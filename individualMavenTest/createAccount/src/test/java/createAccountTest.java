import org.antlr.runtime.tree.Tree;
import org.junit.Test;

import core.*;
import attack.*;

public class createAccountTest {

  @Test 
  public void test1() {
    Administrator admin = new Administrator("admin");
    OS os = new OS("os");

    admin.addOs(os);
    

    Attacker attacker = new Attacker();
    attacker.addAttackPoint(admin.adminRights);
    attacker.attack();

    os.persistance.assertCompromisedInstantaneously();
  }
  @Test
  public void test2() {
    Administrator admin = new Administrator("admin",true,true);
    OS os = new OS("os");

    admin.addOs(os);
    

    Attacker attacker = new Attacker();
    attacker.addAttackPoint(admin.adminRights);
    attacker.attack();

    os.persistance.assertUncompromised();
  }
  @Test 
  public void test3() {
    Administrator admin = new Administrator("admin");
    OS os = new OS("os",true);

    admin.addOs(os);
    

    Attacker attacker = new Attacker();
    attacker.addAttackPoint(admin.adminRights);
    attacker.attack();

    os.persistance.assertUncompromised();
  }
  @Test 
  public void test4() {
    Administrator admin = new Administrator("admin");
    OS os = new OS("os");
    InternalNetwork internalNetwork = new InternalNetwork("internalNetwork",true);


    admin.addOs(os);
    admin.addInternalNetwork(internalNetwork);
    

    Attacker attacker = new Attacker();
    attacker.addAttackPoint(admin.adminRights);
    attacker.attack();

    os.persistance.assertUncompromised();
  }
}