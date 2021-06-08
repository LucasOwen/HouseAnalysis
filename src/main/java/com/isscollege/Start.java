/**
 * 
 */
package com.isscollege;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.isscollege.interceptors.CheckLoginInterceptor;

/**
 * 作者: 杜丹东 D.D.D 日期: 2020年10月16日上午9:48:48
 */
@SpringBootApplication
// @RestController // 当期类中的所有方法返回值都为字符串==controller+responseBody ,
// 当类上没有添加requestmapping时，访问路径为“/”,而且一个项目中只能有一个类不添加此注解
@Controller
@MapperScan("com.isscollege.*")
public class Start implements WebMvcConfigurer {
	public static void main(String[] args) {
		System.out.println("开始啦...");
		SpringApplication.run(Start.class, args);
		System.out.println("结束啦... ");
	}

	@RequestMapping("/hello")
	public String hello() {
		return "hello,springboot";
	}

	@RequestMapping("/index")
	public String index() {
		return "login";
	}

	// 配置错误页面 2.0以上
	@Bean
	public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
		return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
			@Override
			public void customize(ConfigurableWebServerFactory factory) {
				ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
				ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
				factory.addErrorPages(error401Page, error404Page, error500Page);
			}
		};
	}

	@Autowired
	private CheckLoginInterceptor checkLoginInterceptor;

	// 注册拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(checkLoginInterceptor).addPathPatterns("/admin/*");

	}

}
