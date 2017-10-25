import metier.BanqueRemote;
import metier.entities.Compte;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Date;
import java.util.List;

public class ClientRemote {
    public static void main(String[] args) {
        try {
            Context ctx = new InitialContext();
            String appName = "BanqueWeb";
            String moduleName = "BanqueWeb";
            String beanName = "BK";
            String remoteInterface = BanqueRemote.class.getName();
            String name = "ejb:" + appName + "/" + moduleName + "/" + beanName + "!" + remoteInterface;

            BanqueRemote proxy = (BanqueRemote) ctx.lookup(name);

            Compte c1 = new Compte();
            c1.setDate_creation(new Date());
            Compte c2 = new Compte();
            c2.setDate_creation(new Date());
            Compte c3 = new Compte();
            c3.setDate_creation(new Date());
            c3.setSolde(7000);

            proxy.addCompte(c1);
            proxy.addCompte(c2);
            proxy.addCompte(c3);

            Compte cp = proxy.getCompte(1L);
            System.out.println(cp.getSolde());

            proxy.verser(1L, 4000);
            proxy.retirer(1L, 2000);
            proxy.virement(1L,2L ,1000);

            List<Compte> cptes = proxy.listComptes();
            for(Compte c:cptes){
                System.out.println(c.getCode() + " : " + c.getSolde());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
