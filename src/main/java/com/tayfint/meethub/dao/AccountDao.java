package com.tayfint.meethub.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tayfint.meethub.model.Account;
import com.tayfint.meethub.model.Meeting;
import com.tayfint.meethub.model.Membership;

public interface AccountDao extends CrudRepository<Account,Long> {

    Account findByAccountNumber (int accountNumber);
    
    Account findByMembershipAndAcctType(Membership membership, String acctType);
    
    List<Account> findByMeeting(Meeting mtg);
    
    @Modifying
    @Query("update Account a set a.balance = :balance where a.id = :id")
    void updateBalanceByAcctId(@Param("id") Long id, @Param("balance") BigDecimal balance);
    
    Optional<Account> findById(Long id);

	Account findByMeetingAndAcctType(Meeting mtg, String acctType);

	List<Account> findByMembership(Membership membership);
}
