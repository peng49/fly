package fly.admin.repository;

import fly.admin.entity.model.OauthAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthAccountRepository extends JpaRepository<OauthAccount,Integer> {

}
