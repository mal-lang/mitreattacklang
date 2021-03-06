import java.beans.Transient;

import org.junit.Test;

import core.*;
import attack.*;

public class automatedCollectionTest{

    @Test
    public void test1(){
        Administrator administrator = new Administrator("administrator");
        OS os = new OS("os" ,false,false,false,false,false  
                            ,false,false,false,false,false
                            ,false,false,false,false,true
                            ,true,false,false,false,false);
        User user = new User("user");
         

        administrator.addOs(os);
        user.addOs(os);

        Attacker attacker = new Attacker();
        attacker.addAttackPoint(user.userRights);
        attacker.attack();


        os.dataCollected.assertUncompromisedFrom(os._automatedCollection);
        os.sensitiveDataCollected.assertUncompromisedFrom(os._automatedCollection);


    }
    @Test
    public void test2(){
        Administrator administrator = new Administrator("administrator");
        OS os = new OS("os" ,true,true,true,true,true
                            ,true,true,true,true,true
                            ,true,true,true,true,false
                            ,false,false,false,false,false);
        User user = new User("user");

        administrator.addOs(os);
        user.addOs(os);

        Attacker attacker = new Attacker();
        attacker.addAttackPoint(user.userRights);
        attacker.attack();


        os.dataCollected.assertCompromisedInstantaneouslyFrom(os._automatedCollection);
        os.sensitiveDataCollected.assertUncompromised();

    }
    @Test 
    public void test3(){
        Administrator administrator = new Administrator("administrator");
        OS os = new OS("os");
        User user = new User("user");

        administrator.addOs(os);
        user.addOs(os);

        Attacker attacker = new Attacker();
        attacker.addAttackPoint(administrator.adminRights);
        attacker.attack();


        os.dataCollected.assertCompromisedInstantaneously();
        os.sensitiveDataCollected.assertCompromisedInstantaneously();
    }
}
