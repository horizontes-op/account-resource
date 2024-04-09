package insper.store.account;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account create(Account in) {
        in.hash(calculateHash(in.password()));
        in.password(null);
        in.role("user");
        return accountRepository.save(new AccountModel(in)).to();
    }

    public Account read(@NonNull String id) {
        return accountRepository.findById(id).map(AccountModel::to).orElse(null);
    }

    public Account login(String email, String password) {
        String hash = calculateHash(password);
        return accountRepository.findByEmailAndHash(email, hash).map(AccountModel::to).orElse(null);
    }

    private String calculateHash(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            byte[] encoded = Base64.getEncoder().encode(hash);
            return new String(encoded);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    // public Account update(Account in) {
    //     AccountModel model = new AccountModel(in);
    //     return accountRepository.save(model).to();
    // }

    public void delete(@NonNull String id) {
        accountRepository.deleteById(id);
    }

    public Account changeRole(@NonNull String id, @NonNull String role) {
        AccountModel model = accountRepository.findById(id).orElseThrow();
        if (model.role().equals("admin") ) {
            throw new UnsupportedOperationException("Admin role cannot be changed");
        }
        model.role(role);
        return accountRepository.save(model).to();
    }
}
