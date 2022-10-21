package test.scottishpower.smartmeter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.scottishpower.smartmeter.entity.CustomerAccount;

import java.util.List;
import java.util.Optional;

/**
 * repository for CUSTOMERACCOUNT table
 */
@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount,Integer> {

    /**
     * to get the meter ids for gas and electricity for particular customer
     * @param accountNumber
     * @return List<CustomerAccount>
     */
    List<CustomerAccount> findByAccountId(int accountNumber);
}
