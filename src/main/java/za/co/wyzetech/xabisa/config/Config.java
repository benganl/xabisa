package za.co.wyzetech.xabisa.config;

import java.util.concurrent.TimeUnit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@ComponentScan(basePackages = {"za.co.wyzetech"})
@EnableWebMvc
@EnableJpaRepositories
public class Config implements WebMvcConfigurer {

  @Bean
  SpringResourceTemplateResolver templateResolver(final ApplicationContext appCtx) {
    final var tmplResolver = new SpringResourceTemplateResolver();
    tmplResolver.setApplicationContext(appCtx);
    tmplResolver.setCacheable(false);
    tmplResolver.setPrefix("classpath:templates/");
    tmplResolver.setSuffix(".html");
    return tmplResolver;
  }

  @Bean
  SpringTemplateEngine templateEngine(final SpringResourceTemplateResolver templateResolver) {
    final var tmplEngine = new SpringTemplateEngine();
    tmplEngine.setTemplateResolver(templateResolver);
    tmplEngine.setEnableSpringELCompiler(true);
    return tmplEngine;
  }

  @Bean
  ViewResolver viewResolver(final ApplicationContext appCtx, final SpringTemplateEngine templateEngine) {
    final var viewResolver = new ThymeleafViewResolver();
    final var viewResolverReg = new ViewResolverRegistry(null, appCtx);
    viewResolver.setTemplateEngine(templateEngine);
    viewResolverReg.viewResolver(viewResolver);
    return viewResolver;
  }

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**", "/webjars/**")
        .addResourceLocations("classpath:/static/", "/webjars/")
        .setCacheControl(CacheControl.maxAge(0, TimeUnit.SECONDS))
        .resourceChain(false);
  }
}
