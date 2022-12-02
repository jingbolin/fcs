package com.yinhe.ec.core.config;

import java.math.BigInteger;
import java.util.List;
import javax.servlet.MultipartConfigElement;

import com.yinhe.ec.core.interceptor.BaseInterceptor;
import com.yinhe.ec.core.interceptor.MaliciousRequestInterceptor;
import com.yinhe.ec.core.support.http.ReturnValueHandlerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;

import com.alibaba.fastjson.serializer.MyListSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.yinhe.ec.core.filter.CsrfFilter;
import com.yinhe.ec.core.filter.LocaleFilter;
import com.yinhe.ec.core.util.DataUtil;
import com.yinhe.ec.core.util.InstanceUtil;
import com.yinhe.ec.core.util.PropertiesUtil;

import cn.hutool.json.JSONArray;

/**
 * @author ShenHuaJie
 * @since 2018年5月10日 下午2:45:52
 */
@EnableWebMvc
public abstract class WebMvcConfig implements WebMvcConfigurer
{
	@Bean
	public ReturnValueHandlerFactory returnValueHandlerFactory()
	{
		return new ReturnValueHandlerFactory();
	}

	@Bean
	public FilterRegistrationBean<CharacterEncodingFilter> encodingFilterRegistration()
	{
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		FilterRegistrationBean<CharacterEncodingFilter> registration = new FilterRegistrationBean<CharacterEncodingFilter>(encodingFilter);
		registration.setName("encodingFilter");
		registration.addUrlPatterns("/*");
		registration.setAsyncSupported(true);
		registration.setOrder(1);
		return registration;
	}

	@Bean
	public FilterRegistrationBean<LocaleFilter> localeFilterRegistration()
	{
		FilterRegistrationBean<LocaleFilter> registration = new FilterRegistrationBean<LocaleFilter>(new LocaleFilter());
		registration.setName("localeFilter");
		registration.addUrlPatterns("/*");
		registration.setOrder(2);
		return registration;
	}

	// @Bean
	public FilterRegistrationBean<CsrfFilter> csrfFilterRegistration()
	{
		FilterRegistrationBean<CsrfFilter> registration = new FilterRegistrationBean<CsrfFilter>(new CsrfFilter());
		registration.setName("csrfFilter");
		registration.addUrlPatterns("/*");
		registration.setOrder(3);
		return registration;
	}

	public abstract BaseInterceptor eventInterceptor();

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry)
	{
		registry.jsp("/WEB-INF/jsp/", ".jsp");
		registry.enableContentNegotiation(new JstlView());
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addViewController("/").setViewName("redirect:/index.html");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
	{
		configurer.enable();
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
	{
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		List<MediaType> mediaTypes = InstanceUtil.newArrayList();
		mediaTypes.add(MediaType.valueOf("application/json;charset=UTF-8"));
		mediaTypes.add(MediaType.valueOf("text/html"));
		converter.setSupportedMediaTypes(mediaTypes);
		FastJsonConfig config = new FastJsonConfig();
		config.setSerializerFeatures(SerializerFeature.QuoteFieldNames, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty);
		config.setSerializeConfig(new SerializeConfig());
		config.getSerializeConfig().put(BigInteger.class, ToStringSerializer.instance);
		config.getSerializeConfig().put(Long.class, ToStringSerializer.instance);
		// 解决集合中的Long和Integer无法自定义序列化的bug
        config.getSerializeConfig().put(JSONArray.class , MyListSerializer.instance);
        config.getSerializeConfig().put(List.class , MyListSerializer.instance);
		converter.setFastJsonConfig(config);
		converters.add(converter);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		MaliciousRequestInterceptor requestInterceptor = new MaliciousRequestInterceptor();
		if (DataUtil.isNotEmpty(PropertiesUtil.getInt("request.minInterval")))
		{
			requestInterceptor.setMinRequestIntervalTime(PropertiesUtil.getInt("request.minInterval"));
		}
		requestInterceptor.setNextInterceptor(eventInterceptor());
		registry.addInterceptor(requestInterceptor).addPathPatterns("/**").excludePathPatterns("/*.ico", "/*/api-docs", "/swagger**", "/swagger-resources/**", "/webjars/**",
				"/configuration/**");
		// TokenInterceptor tokenInterceptor=new TokenInterceptor();
		// registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns("/*.ico", "/*/api-docs",
		// "/swagger**", "/swagger-resources/**", "/webjars/**", "/configuration/**");
		// LoginSessionTimeOutInterceptor loginSessionTimeOutInterceptor=new LoginSessionTimeOutInterceptor();
		// registry.addInterceptor(loginSessionTimeOutInterceptor).addPathPatterns("/**").excludePathPatterns("/*.ico",
		// "/*/api-docs",
		// "/swagger**", "/swagger-resources/**", "/webjars/**",
		// "/configuration/**","/v2/**","/favicon.ico","*.js","*.css","/*.html","**iconfont.woff","*.gif","**/dist/**","**/mp/**");
		// .js
		// .css
		// .html
		// iconfont.woff
		// .gif
		/// user/doLogin
		/// dist/
	}

	/** 文件上传配置 */
	@Bean
	public MultipartConfigElement multipartConfigElement()
	{
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 文件最大
		factory.setMaxFileSize("1024MB");
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("1024MB");
		return factory.createMultipartConfig();
	}

	// 资源重定向(仅作为后台使用不提供静态资源)
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("upload/**").addResourceLocations("/WEB-INF/upload/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/", "classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/");
	}

	// 允许跨域的接口
	public CorsConfiguration corsConfig()
	{
		CorsConfiguration corsConfiguration = new CorsConfiguration();

		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setMaxAge(3600L);
		return corsConfiguration;
	}

	@Bean
	public CorsFilter corsFilter()
	{
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig());
		return new CorsFilter(source);
	}

}
