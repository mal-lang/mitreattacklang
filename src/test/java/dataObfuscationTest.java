import org.junit.Test;

import core.*;
import attack.*;

public class dataObfuscationTest{

  @Test
  public void testWithoutDetectionAndPreventionSystems(){
    // Section 1: Asset instantiation
    OS os = new OS("os");
    User user = new User("user");
    Adversary adversary = new Adversary("adversary");
    Computer computer = new Computer("computer");
    ExternalNetwork externalNetwork = new ExternalNetwork("externalNetwork");

    // Section 2: Asset connections and attacker creation
    adversary.addUser(user);
    adversary.addExternalNetwork(externalNetwork);
    adversary.addComputer(computer);

    computer.addOs(os);
    computer.addExternalNetwork(externalNetwork);
    user.addComputer(computer);
 
    
    Attacker attacker = new Attacker();
    attacker.addAttackPoint(user.stolenCredentials);
    attacker.attack();

    // Section 3: Assertions
    adversary.obfuscateData.assertCompromisedInstantaneously();
    externalNetwork.upload.assertCompromisedInstantaneouslyFrom(adversary.obfuscateData);
  }

  @Test
  public void testWithDetectionAndPreventionSystems() {
     // Section 1: Asset instantiation
     OS os = new OS("os");
     User user = new User("user");
     Adversary adversary = new Adversary("adversary");
     Computer computer = new Computer("computer");
     ExternalNetwork externalNetwork = new ExternalNetwork("externalNetwork",true,false);
 
     // Section 2: Asset connections and attacker creation
     adversary.addUser(user);
     adversary.addExternalNetwork(externalNetwork);
     adversary.addComputer(computer);

     computer.addOs(os);
     computer.addExternalNetwork(externalNetwork);
     user.addComputer(computer);
     Attacker attacker = new Attacker();
     attacker.addAttackPoint(user.stolenCredentials);
     attacker.attack();
 
     // Section 3: Assertions
     adversary.obfuscateData.assertCompromisedInstantaneously();
     externalNetwork.upload.assertUncompromisedFrom(adversary.obfuscateData);
  }
}