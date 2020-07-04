import java.io.*;

public class PodatkovnaBaza
{
    public static void main(String[] args)
    {
        SeznamiUV seznamiUV = new SeznamiUV();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        String output;
        
        seznamiUV.addImpl("pv", new PrioritetnaVrsta<>(new BolnikPrimerjajPoImenu()), new PrioritetnaVrsta<>(new BolnikPrimerjajPoEMSO()));
        seznamiUV.addImpl("sk", new Sklad<>(new BolnikPrimerjajPoImenu()), new Sklad<BolnikObj>(new BolnikPrimerjajPoEMSO()));
        seznamiUV.addImpl("bst", new Bst<>(new BolnikPrimerjajPoImenu()), new Bst<>(new BolnikPrimerjajPoEMSO()));
        seznamiUV.addImpl("bk", new BinomskaKopica<>(new BolnikPrimerjajPoImenu()), new BinomskaKopica<>(new BolnikPrimerjajPoEMSO()));
        

        try
        {
            do
            {
                System.out.print("Enter command: ");
                input = br.readLine();
                output = seznamiUV.processInput(input);
                System.out.println(output);
            }
            while (!input.equalsIgnoreCase("exit"));
        }
        catch (IOException e)
        {
            System.err.println("Failed to retrieve the next command.");
            System.exit(1);
        }
    }
}
