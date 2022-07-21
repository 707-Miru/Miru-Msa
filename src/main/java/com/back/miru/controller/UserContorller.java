package com.back.miru.controller;

import com.back.miru.model.dto.Interest;
import com.back.miru.model.dto.User;
import com.back.miru.model.service.JwtService;
import com.back.miru.model.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/user")
public class UserContorller {
	public static final Logger logger = LoggerFactory.getLogger(UserContorller.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;

	@GetMapping("/check/{id}")
	public int checkId(@PathVariable String id) throws Exception {
		int cnt = 1;
		cnt = userService.checkId(id);
		return cnt;
	}

	@PostMapping
	public ResponseEntity<Integer> registerUser(@RequestBody Map<String, String> map) throws Exception {
		userService.registerUser(map);
		return new ResponseEntity<Integer>(1, HttpStatus.OK);
	}

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginUser(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) User user) {
		System.out.println("membercontoller 호출");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User loginUser = userService.loginUser(user.getId(), user.getPassword());
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getId(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getUserInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
				User user = userService.infoUser(userid);
				resultMap.put("userInfo", user);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원정보수정", notes = "", response = Map.class)
	@PutMapping("/{userid}")
	public ResponseEntity<Map<String, Object>> modifyUser(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) @PathVariable String userid,
			@RequestBody Map<String, String> map) {
		System.out.println("membercontoller 호출");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			userService.updateUser(map);
			resultMap.put("userInfo", map);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;

		} catch (Exception e) {
			logger.error("수정 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원정보삭제", notes = "", response = Map.class)
	@PostMapping("/delete/{userid}")
	public ResponseEntity<Map<String, Object>> deleteUser(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) @PathVariable String userid) {
		System.out.println("membercontoller 호출");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		System.out.println(userid);

		try {
			userService.deleteUser(userid);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;

		} catch (Exception e) {
			logger.error("삭제 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("{id}")
	public ResponseEntity<Integer> checkPasswordFind(@PathVariable String id, @RequestBody User user) throws Exception {
		int cnt = 0;
		cnt = userService.checkPasswordFind(user);
		return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
	}

	@GetMapping("/password/{id}")
	public ResponseEntity<Integer> selectPassword(@PathVariable String id, @RequestParam String password) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("password", password);

		int cnt = 0;
		cnt = userService.checkPassword(map);
		return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
	}

	@PutMapping("/password/{id}")
	public ResponseEntity<Integer> updatePassword(@PathVariable String id, @RequestBody Map<String, String> map)
			throws Exception {
		int cnt = 0;
		cnt = userService.updatePw(map);
		return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
	}

	@GetMapping("/interest/{id}")
	public ResponseEntity<List<Interest>> getInterestList(@PathVariable String id) throws Exception {
		return new ResponseEntity<List<Interest>>(userService.getInterestList(id), HttpStatus.OK);
	}

	@PostMapping("/interest")
	public ResponseEntity<String> addInterest(@RequestBody Map<String, String> map) throws Exception {
		if (userService.resisterInterest(map)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/interest/remove")
	public ResponseEntity<String> deleteInterest(@RequestBody Map<String, String> map) throws Exception {
		if (userService.deleteInterest(map)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
