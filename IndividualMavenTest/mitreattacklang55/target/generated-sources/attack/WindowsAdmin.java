package attack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertTrue;

import core.Asset;
import core.AttackStep;
import core.AttackStepMax;
import core.AttackStepMin;
import core.Defense;
public class WindowsAdmin extends Asset {
   public Administrator administrator;
   public Windows windows;

   public SystemRights systemRights;

   public WindowsAdmin() {
      super();
      AttackStep.allAttackSteps.remove(systemRights);
      systemRights = new SystemRights(this.name);
      assetClassName = "WindowsAdmin";
   }

   public WindowsAdmin(String name) {
      super(name);
      AttackStep.allAttackSteps.remove(systemRights);
      systemRights = new SystemRights(this.name);
      assetClassName = "WindowsAdmin";
   }


   public class SystemRights extends AttackStepMin {
   public SystemRights(String name) {
      super(name);
      assetClassName = "WindowsAdmin";
   }
@Override
public void updateChildren(java.util.Set<AttackStep> activeAttackSteps) {
if (administrator != null) {
administrator.adminRights.updateTtc(this, ttc, activeAttackSteps);
}
if (windows != null) {
windows.executeService.updateTtc(this, ttc, activeAttackSteps);
}
if (windows != null) {
windows.queryRegistery.updateTtc(this, ttc, activeAttackSteps);
}
if (windows != null) {
windows.adminSecuritySoftwareDiscovery.updateTtc(this, ttc, activeAttackSteps);
}
}
      @Override
      public double localTtc() {
         return ttcHashMap.get("WindowsAdmin.systemRights");
      }

   }

      public void addAdministrator(Administrator administrator) {
         this.administrator = administrator;
         administrator.windowsAdmin = this;
      }

      public void addWindows(Windows windows) {
         this.windows = windows;
         windows.windowsAdmin.add(this);
      }

   @Override
   public String getAssociatedAssetClassName(String roleName) {
      if (roleName.equals("administrator")) {
         return administrator.getClass().getName();
      }
      if (roleName.equals("windows")) {
         return windows.getClass().getName();
      }
      return null;
   }
   @Override
   public java.util.Set<Asset> getAssociatedAssets(String roleName) {
      java.util.Set<Asset> assets = new java.util.HashSet<>();
      if (roleName.equals("administrator")  && administrator != null) {
         assets.add(administrator);
         return assets;
      }
      if (roleName.equals("windows")  && windows != null) {
         assets.add(windows);
         return assets;
      }
      assertTrue("The asset " + this.toString() + " does not feature the role name " + roleName + ".", false);
      return null;
   }
   @Override
   public java.util.Set<Asset> getAllAssociatedAssets() {
      java.util.Set<Asset> assets = new java.util.HashSet<>();
      if (administrator != null) {
         assets.add(administrator);
      }
      if (windows != null) {
         assets.add(windows);
      }
      return assets;
   }
}