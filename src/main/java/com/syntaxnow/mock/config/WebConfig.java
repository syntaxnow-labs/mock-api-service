package com.syntaxnow.mock.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Global web configuration for the service.
 *
 * <p>This configuration class customizes how Spring MVC handles URL paths and
 * Cross-Origin Resource Sharing (CORS) policies across the application.
 *
 * <h2>Features:</h2>
 * <ul>
 *   <li><b>Trailing Slash Matching:</b> Treats URLs with and without trailing slashes
 *       (e.g., <code>/api/workflows</code> and <code>/api/workflows/</code>) as equivalent.</li>
 *   <li><b>Global CORS Policy:</b> Allows cross-origin requests for all endpoints,
 *       enabling the front-end (React/Plasmic, etc.) to interact with backend APIs.</li>
 * </ul>
 *
 * <p>For production environments, it is strongly recommended to:
 * <ul>
 *   <li>Restrict allowed origins to specific trusted domains.</li>
 *   <li>Review <code>allowCredentials</code> settings based on authentication requirements.</li>
 * </ul>
 *
 * @author Raja
 * @since 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Enables Spring to treat URLs with and without trailing slashes as equivalent routes.
     *
     * @param configurer the {@link PathMatchConfigurer} used to customize path matching options
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseTrailingSlashMatch(true);
    }

    /**
     * Configures global Cross-Origin Resource Sharing (CORS) settings for all endpoints.
     *
     * <p>Currently allows all origins, headers, and methods to simplify API consumption
     * from different domains or front-end clients during development.</p>
     *
     * @param registry the {@link CorsRegistry} for configuring allowed origins and HTTP methods
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Replace with specific domains for production
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
    }
}
