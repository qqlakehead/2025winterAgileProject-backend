package com.neko.seed;

import com.neko.seed.auth.enums.TokenSubject;
import com.neko.seed.auth.service.TokenService;
import com.neko.seed.user.service.UserService;
import com.neko.seed.user.service.impl.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApplicationTests {


	@Autowired
	private TokenService tokenService;
	@Test
	public void contextLoads() {
	}

	@Test
	public void buildToken(){
		// 生成tokne
		String generate = tokenService.generate(TokenSubject.ACCESS.type(), 1L, 1);
		log.info("生成token:【{}】",generate);
		//解析token
		Jws<Claims> parse = tokenService.parse(TokenSubject.ACCESS.type(), generate);
		log.info(parse.getBody().get("id").toString());
	}

	@Test
	public void parseToken(){
		Jws<Claims> parse = tokenService.parse(TokenSubject.ACCESS.type(),
				"eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwic3ViIjoiQUNDRVNTIiwiZXhwIjoxNzM2NjYyNzE5fQ.UlIwLR88tX-g204gIVWBYDuPJ3e1XjHTYKJY1QgPDbs");
		parse.getBody().get("id");
	}


}
