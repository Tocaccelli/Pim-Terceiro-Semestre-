package br.com.localfarm.app.infrastructure.persistence.config;

public class JwtUtil {

    public static final String[] ENDPOINTS_WITH_USER_CAN_ACCESS = {
            "/resources/**",
            "/static**",
            "/static/**",
            "/static/styles/**",
            "/static/image/**",
            "/static/img/**",
            "/styles/**",
            "/image/**",
            "/templates/**",
            "/templates/auth/**",
            "/templates/email/**",
            "/static/js/**",
            "/templates/principal/**",
    };

    public static final String[] ENDPOINTS_WITH_ADMIN_CAN_ACCESS = {
            "/users/**",
            "/admin/**"
    };

    public static final String[] ENDPOINTS_WITH_MANAGER_CAN_ACCESS = {
            "/units-of-measure/**",
            "/clients/**",
            "/products/**"
    };

    public static final String[] ENDPOINTS_WITH_OPERATOR_CAN_ACCESS = {
            "/product-movements/**"
    };
}