package uz.pdp.lesson71appnewssite.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.lesson71appnewssite.entity.User;

import java.util.Optional;

public class SecurityAuditing implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")){
            User user =(User) authentication.getPrincipal();
            return Optional.of(user.getId());
        }

        return Optional.empty();
    }
}
