package data_providers;

import dto.UserLombok;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class UserDataProvider {
    @DataProvider
    public Iterator<UserLombok> wrongLoginData() {
        return getUsersFromCsv(
                "src/test/resources/wrong_login.csv"
        );
    }
    @DataProvider
    public Iterator<UserLombok> wrongRegistrationData() {
        return getUsersFromCsv(
                "src/test/resources/wrong_registration.csv"
        );
    }

    private Iterator<UserLombok> getUsersFromCsv(String path) {
        List<UserLombok> list = new ArrayList<>();
        try (BufferedReader bufferedReader =
                        new BufferedReader(new FileReader(path))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitLine = line.split(",", -1);
                list.add(
                        UserLombok.builder()
                                .username(splitLine[0])
                                .password(splitLine[1])
                                .build()
                );
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list.iterator();
    }
}



//
//public class UserDataProvider {
//    @DataProvider
//    public Iterator<UserLombok> dataProviderWrongPasswordOrEmail(){
//        List<UserLombok> list = new ArrayList<>();
//        try(BufferedReader bufferedReader =new BufferedReader(new FileReader
//                ("src/test/resources/wrong_email_password.csv"))){
//            String line = bufferedReader.readLine();
//            while (line !=null){
//                String[] splitLine = line.split(",", -1);
//                list.add(UserLombok.builder()
//                                .username(splitLine[0])
//                                .password(splitLine[1])
//                                .build());
//                line = bufferedReader.readLine();
//            }
//        } catch (IOException e){
//            e.printStackTrace();
//            System.out.println("created exception");
//        }
//        return list.listIterator();
//    }
//}
