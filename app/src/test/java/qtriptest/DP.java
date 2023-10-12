package qtriptest;
import org.testng.annotations.DataProvider;


public class DP {
    @DataProvider(name = "loginTestData")
    public static Object[][] loginTestData() {
        return new Object[][] {
            {"testuser@gmail.com", "abc@123"},
            {"TESTUSER@gmail.com", "123456"},
            {"123_USER_low@gmail.com", "QWERTY!!!!"}
        };
    }

    @DataProvider(name = "filterTestData")
    public static Object[][] filterTestData() {
        return new Object[][] {
            {"Bengaluru", "Cycling Routes", "6-12 Hours", 1, 9},
            {"Singapore", "Hillside Getaways", "6-12 Hours", 1, 8},
            {"Goa", "Party Spots", "2-6 Hours", 1, 11},
            // Add more test data rows as needed
        };
    }

    @DataProvider(name = "bookingTestDatat3")
    public static Object[][] bookingTestData() {
        return new Object[][] {
            {"TC3_1@gmail.com", "abc@123", "Bengaluru", "Niaboytown", "palam", "16-11-2025", "2"},
            {"TC3_2@gmail.com", "abc@123", "Singapore", "Grand Ashland", "biden", "26-11-2025", "4"},
            {"TC3_3@gmail.com", "abc@123", "Bangkok", "Tifwales Ferry", "prabhu", "26-12-2025", "1"},
            // Add more test data sets if needed
        };
    }

   // Define a data provider method that provides test data
   @DataProvider(name = "bookingTestData")
   public static Object[][] provideBookingTestData() {
       return new Object[][] {
           // Row 1
           {
               "TC4_1@gmail.com",
               "abc@123",
               "Bengaluru;Niaboytown;Chicky;01-01-2025;2",
               "Kolkata;Thton;Cha Cha;01-02-2025;3",
               "Malaysia;Wootkentree;Bom Bom;02-12-2025;1"
           },
           // Row 2
           {
            "TC4_2@gmail.com",
            "abc@321",
            "Bengaluru;Fort Sionnnn;Chicky;01-01-2025;2",
            "Kolkata;North Gelgoon;Cha Cha;01-02-2025;3",
            "Malaysia;Wootkentree;Lya;02-12-2025;1"
        },
        // Row 3
        {
            "TC4_3@gmail.com",
            "abc@321",
            "Goa;Perthby;Chicky;01-01-2025;2",
            "Bengaluru;Niaboytown;Chicky;01-01-2025;2",
            "Bengaluru;Fort Sionnnn;Chicky;01-01-2025;2"
        }
    };
}
}
