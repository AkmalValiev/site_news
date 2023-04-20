package uz.pdp.lesson71appnewssite.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.lesson71appnewssite.entity.enums.Permission;
import uz.pdp.lesson71appnewssite.entity.template.AbstractEntity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User extends AbstractEntity implements UserDetails {

    @Column(nullable = false)
    private String fullNAme;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Role role;

    private boolean enabled;
    private boolean accountNonExpired=true;
    private boolean accountNonLocked=true;
    private boolean credentialsNonExpired=true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Permission> permissions = this.role.getPermissions();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Permission permission : permissions) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.name()));
        }
        return grantedAuthorities;
    }

    public User(String fullNAme, String username, String password, Role role, boolean enabled) {
        this.fullNAme = fullNAme;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }
}
