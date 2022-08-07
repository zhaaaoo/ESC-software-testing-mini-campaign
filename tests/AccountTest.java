import org.junit.Assert;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.jupiter.params.provider.CsvFileSource;

@RunWith(Parameterized.class)

public class AccountTest {

    public Account account1, account2;
    public Boolean result;

    // constructor for test class
    public AccountTest(Boolean result, Account account1, Account account2) {
        this.result = result;
        this.account1 = account1;
        this.account2 = account2;
    }

    // Parameters derived from equivalence class testing with boundary value analysis
    @Parameters public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object [][] {
                {false,
                    new Account("ID1", "BOS12345", "SAVINGS", "USD", "1000"),
                        new Account("ID1", "BOS12345", "SAVINGS", "SAVINGS", "500")
                },
                {true,
                    new Account("ID2", "BOS12346", "SAVINGS", "SAVINGS", "1000"),
                        new Account("ID2", "BOS12346", "SAVINGS", "USD", "1000")
                },
                {false,
                    new Account("ID3", "BOS12347", "SAVINGS", "USD", "1000"),
                        new Account("ID3", "BOS12347", "SAVINGS", "USD", "2000")
                }
        });
    }

    // Checks that the equals function compares the accounts correctly
    @Test public void compareAccountTest() {
        assertEquals(result, account1.equals(account2));
    }

}
