package ui;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.github.javafaker.Faker;

import model.Business.Business;
import model.Business.ConfigureABusiness;
import model.CustomerManagement.ChannelCatalog;
import model.CustomerManagement.MarketCatalog;
import model.MarketModel.Market;
import model.OrderManagement.MasterOrderList;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.SolutionOffer;
import model.ProductManagement.SolutionOfferCatalog;
import model.ProductManagement.SolutionOfferOrderItem;
import model.ProductManagement.MasterSolutionOrderList;
import model.ProductManagement.Product;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;

public class DigitalMarketingApplication {

    private static final double minPrice = 50.0;
    private static final double maxPrice = 150.0;
  

    public static void main(String[] args) {
        Business business = ConfigureABusiness.createABusinessAndLoadALotOfData("Xerox", 50, 10, 30, 100, 10);

        SupplierDirectory sd = business.getSupplierDirectory();
        MasterSolutionOrderList msol = MasterSolutionOrderList.getInstance();
        ChannelCatalog cc = business.getChannelCatalog();
        MarketCatalog mkc = business.getMarketCatalog();
        ProductCatalog pc = business.getProductCatalog();
        MasterOrderList mol = business.getMasterOrderList();
        SolutionOfferCatalog soc = business.getSolutionOfferCatalog();

        Scanner sc = new Scanner(System.in);
        boolean exitCode = false;

        while (!exitCode) {
            System.out.println("Welcome to Arbon Enterprises. Please pick an option:");
            System.out.println("For Customers");
            System.out.println("1. Browse top bundles this month");
            System.out.println("2. Browse and search for bundles by price range");
            System.out.println("3. Browse and search for bundles by market demographics");
            System.out.println("4. Browse and search for bundles by channels");
            System.out.println("5.Exit");
            System.out.println("______________________________");
            System.out.println("For Businesses");
            System.out.println("6. View Overall Sales Report");
            System.out.println("7. Browse sales reports by Market Demographics");
            System.out.println("8. Browse sales reports by Channels");
            System.out.println("9. Exit");

            String input = sc.next();
            System.out.println(input);
            switch (input) {
                case "1":
                    topBundlesThisMonth(sd, msol);
                    break;
                case "2":
                    browseByPriceRange(sd, msol);
                    break;
                case "3":
                    browseByMarketDemographics(sd, msol, mkc);
                    break;
                case "4":
                    browseByChannels(sd, cc, msol);
                    break;
                case "5":
                    exitCode = true;
                    break;
                case "6":
                    overallSalesReport(soc, mol, mkc, sd, pc, cc);
                    break;
                case "7":
                    salesReportByMarketDemographics(soc, mol, mkc, sd, pc, cc);
                    break;
                case "8":
                    salesReportByChannels(soc, mol, mkc, sd, pc, cc);
                    break;
                case "9":
                    exitCode = true;
                    break;
                default:
                    System.out.println("Invalid option");
            }

            System.out.println("Thank you, have a nice day.");
        }

        sc.close();
    }

    private static void topBundlesThisMonth(SupplierDirectory sd, MasterSolutionOrderList msol) {
        SolutionOffer solutionOffer = msol.createSolutionOffer(generateRandomBundleName());
        solutionOffer.setProducts(sd.getSupplierList().get(0).getProductCatalog().getProductList());
        solutionOffer.setPrice(generateRandomPrice(maxPrice, maxPrice));

        System.out.println("Top Bundle This Month:");
        displaySolutionOffer(solutionOffer);
    }

    private static void browseByPriceRange(SupplierDirectory sd, MasterSolutionOrderList msol) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Pick your price range");
        System.out.println("1. Low: $0 - $50");
        System.out.println("2. Average: $50 - $150");
        System.out.println("3. High: $150+");

        String priceRangeInput = sc.next();

        double minPrice = 0;
        double maxPrice = Double.MAX_VALUE;
        switch (priceRangeInput) {
            case "1":
                maxPrice = 50;
                break;
            case "2":
                minPrice = 50;
                maxPrice = 150;
                break;
            case "3":
                minPrice = 150;
                break;
            default:
                System.out.println("Invalid input. Using the default price range.");
        }

        List<SolutionOffer> solutionOffers = generateRandomSolutionOffers(sd, msol, minPrice,
                maxPrice);

        if (!solutionOffers.isEmpty()) {
            System.out.println("Bundles available for the selected price range:");
            for (SolutionOffer offer : solutionOffers) {
                displaySolutionOffer(offer);
            }
        } else {
            System.out.println("No matching Bundles available for the selected price range");
        }
    }

    private static List<SolutionOffer> generateRandomSolutionOffers(SupplierDirectory sd,
            MasterSolutionOrderList msol, double minPrice, double maxPrice) {
        List<SolutionOffer> solutionOffers = new ArrayList<>();

            SolutionOffer solutionOffer = msol.createSolutionOffer(generateRandomBundleName());
            solutionOffer.setProducts(sd.getSupplierList().get(0).getProductCatalog().getProductList());
            solutionOffer.setPrice(generateRandomPrice(minPrice, maxPrice));
            double offerPrice = solutionOffer.getPrice();

            if (offerPrice >= minPrice && offerPrice <= maxPrice) {
                solutionOffers.add(solutionOffer);
         }

        return solutionOffers;
    }

    private static String generateRandomBundleName() {
        Random random = new Random();
        return "Bundle" + " " + (random.nextInt(100) + 1);
    }

    private static double generateRandomPrice(double minPrice, double maxPrice) {
        Random random = new Random();
        double price = minPrice + (random.nextDouble() * (maxPrice - minPrice));
        return Double.parseDouble(new DecimalFormat("0.00").format(price));
    }

    private static void displaySolutionOffer(SolutionOffer solutionOffer) {
        Faker faker = new Faker();
        DecimalFormat priceFormatter = new DecimalFormat("$#,##0.00");
        BigDecimal limitedPrice = BigDecimal.valueOf(solutionOffer.getPrice()).min(BigDecimal.valueOf(10000));
        List<Product> products = solutionOffer.getProducts();
        int numberOfProductsInBundle = Math.max(2, Math.min(5, products.size()));
    
        System.out.println("Bundle: " + solutionOffer.getBundle());
        System.out.println(faker.number().numberBetween(1, 100) + " " + "People Used This Bundle");
        System.out.println("Promotion Code: " + faker.commerce().promotionCode());
        System.out.println("Price: " + priceFormatter.format(limitedPrice.doubleValue()));
        System.out.println("Number of Products in Bundle: " + numberOfProductsInBundle);
        System.out.println(" ");
        System.out.println("Products in this bundle:");
        System.out.println(solutionOffer.getProductsString(numberOfProductsInBundle));
    }

    private static String generateRandomChannelName() {
        Faker faker = new Faker();
        String channelName = faker.company().industry() + " " + faker.company().buzzword() + " Channel";
        return channelName;
    }

    private static String generateRandomMarket() {
        String ageGroup = generateRandomAgeGroup();
        String gender = generateRandomGender();

        String marketName = ageGroup + " " + gender + " Market";
        return marketName;
    }

    private static String generateRandomAgeGroup() {
        String[] ageGroups = { "Child", "Teen", "Adult", "Senior" };
        return ageGroups[(int) (Math.random() * ageGroups.length)];
    }

    private static String generateRandomGender() {
        String[] genders = { "Male", "Female" };
        return genders[(int) (Math.random() * genders.length)];
    }

    private static void browseByMarketDemographics(SupplierDirectory sd,
            MasterSolutionOrderList msol, MarketCatalog marketCatalog) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Pick your market demographics");

        System.out.println("1. Child, Male");
        System.out.println("2. Child, Female");
        System.out.println("3. Teen, Male");
        System.out.println("4. Teen, Female");
        System.out.println("5 Adult, Male");
        System.out.println("6. Adult, Female");

        String marketInput = sc.next();
        List<SolutionOffer> solutionOffers = generateRandomSolutionOffers(sd, msol, minPrice,
                maxPrice);

        if (!solutionOffers.isEmpty()) {
            System.out.println("Bundles available to the selected market demographic:");
            for (SolutionOffer offer : solutionOffers) {
                displaySolutionOffer(offer);
            }
        } else {
            System.out.println("No matching Bundles found in the selected market demographic.");
        }
    }

    private static void browseByChannels(SupplierDirectory sd, ChannelCatalog cc,
            MasterSolutionOrderList msol) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Pick your channel");
        System.out.println("1. Social Media");
        System.out.println("2. Email");
        System.out.println("3. SMS");
        System.out.println("4. Mobile App");
        System.out.println("5. Website");

        String channelInput = sc.next();

        List<SolutionOffer> solutionOffers = generateRandomSolutionOffers(sd, msol, minPrice,
                maxPrice);

        if (!solutionOffers.isEmpty()) {
            System.out.println("Bundles available to the selected channel:");
            for (SolutionOffer offer : solutionOffers) {
                displaySolutionOffer(offer);
            }
        } else {
            System.out.println("No matching Bundles available to the selected channel.");
        }
    }
    private static void overallSalesReport( SolutionOfferCatalog soc,
            MasterOrderList mol, MarketCatalog mkc,
            SupplierDirectory sd, ProductCatalog pc, ChannelCatalog cc) {
        Scanner sc = new Scanner(System.in);
        Faker faker = new Faker();

        System.out.println("Overall Sales Report for Arbon Enterprises");
        System.out.println("Total Sales Volume: $" + mol.getSalesVolume());
        System.out.println("Target Sales: $"+faker.number().numberBetween(1000, 50000));
        System.out.println("Most Successful Channel: " + generateRandomChannelName());
        System.out.println("Most Successful Advertisements: " + faker.commerce().department() + " Ads");
        System.out.println("Top Supplier: " + sd.getSupplierList().get(0).getName());
        System.out.println("Top Product: " + faker.commerce().productName());
        System.out.println("Most Successful Market: " + generateRandomMarket() + " Revenue from the Market:  $" +
                faker.number().numberBetween(1, 1000));
        System.out.println("Best Selling Bundle: " + "Bundle" + " " + faker.number().numberBetween(1, 50));
    }
    private static void salesReportByMarketDemographics(SolutionOfferCatalog soc,
            MasterOrderList mol, MarketCatalog mkc,
            SupplierDirectory sd, ProductCatalog pc, ChannelCatalog cc) {
        Scanner sc = new Scanner(System.in);
        Faker faker = new Faker();

        System.out.println("1. Market Summary Report");
        System.out.println("2. Pick your market demographics");

        String input = sc.next();
        if (input.equals("1")) {
            
            Random random = new Random();
            System.out.println("Market Summary Report for all markets under Arbon Enterprises");
            System.out.println("Total Sales Volume: $" + mol.getSalesVolume());
            System.out.println("Target Sales: $"+faker.number().numberBetween(1000, 50000));
            System.out.println("Most Successful Channel: " + generateRandomChannelName());
            System.out.println("Most Successful Advertisements: " + faker.commerce().department() + " Ads");
            System.out.println("Top Supplier: " + sd.getSupplierList().get(0).getName());
            System.out.println("Top Product: " + faker.commerce().productName());
            System.out.println("Most Successful Market: " + generateRandomMarket() + " Revenue from the Market:  $" +
                    faker.number().numberBetween(1, 1000));
            System.out.println("Best Selling Bundle: " + "Bundle" + " " + random.nextInt(50) + 1);

        } else if (input.equals("2")) {
            System.out.println("Pick your market demographics:");
            System.out.println("1. Child, Male");
            System.out.println("2. Child, Female");
            System.out.println("3. Teen, Male");
            System.out.println("4. Teen, Female");
            System.out.println("5. Adult, Male");
            System.out.println("6. Adult, Female");

            String marketInput = sc.next();
            
            Supplier topSupplier = sd.getSupplierList().get(0);
            Random random = new Random();
            System.out.println("Market Summary Report for selected demographic");
            System.out.println("Total Sales Volume: $" + mol.getSalesVolume());
            System.out.println("Target Sales: $"+faker.number().numberBetween(1000, 50000));
            System.out.println("Most Successful Advertisements: " + faker.commerce().department() + " Ads");
            System.out.println("Advertisement Budget: $" +faker.number().numberBetween(1500, 20000));
            System.out.println("Total Number of Orders: " + faker.number().numberBetween(1, 100));
            System.out.println("Total Number of Customers: " + faker.number().numberBetween(1, 100));
            System.out.println("Top Supplier: " + topSupplier.getName());
            System.out.println("Top Product:" + faker.commerce().productName() + " " +
                    "From Supplier: " + sd.getSupplierList().get(0).getName());
            System.out.println("Most Successful Channel for the selected demographic: " + generateRandomChannelName());
            System.out.println("Best Selling Bundle: " + "Bundle" + " " + random.nextInt(50) + 1);
        }
    }

    private static void salesReportByChannels(SolutionOfferCatalog soc,
            MasterOrderList mol, MarketCatalog mkc,
            SupplierDirectory sd, ProductCatalog pc, ChannelCatalog cc) {
        Scanner sc = new Scanner(System.in);
        Faker faker = new Faker();

        System.out.println("1. Channel Summary Report");
        System.out.println("2. Pick your channel");

        String input = sc.next();

        if (input.equals("1")) {
            Random random = new Random();
            System.out.println("Market Summary Report for all channels used by Arbon Enterprises");
            System.out.println("Total Sales Volume: $" + mol.getSalesVolume());
            System.out.println("Market that spent the most: " + generateRandomMarket() + " Total Market Revenue: $" +
                    faker.number().numberBetween(1, 1000));
            System.out.println("Top Supplier: " + sd.getSupplierList().get(0).getName());
            System.out.println("Top Product:" + faker.commerce().productName() + " " +
                    "From Supplier: " + sd.getSupplierList().get(0).getName());
            System.out.println("Most Successful Advertisements: " + faker.commerce().department() + " Ads");
            System.out.println("Advertisement Budget: $" +faker.number().numberBetween(1500, 20000));
            System.out.println("Most Successful Channel:" + generateRandomChannelName());
            System.out.println("Best Selling Bundle: " + "Bundle" + " " + random.nextInt(50) + 1);

        } else if (input.equals("2")) {
            System.out.println("Pick your channel:");
            System.out.println("1. Social Media");
            System.out.println("2. Email");
            System.out.println("3. SMS");
            System.out.println("4. Mobile App");
            System.out.println("5. Website");

            String channelInput = sc.next();
            Supplier topSupplier = sd.getSupplierList().get(0);
            Random random = new Random();
            System.out.println("Market Summary Report for selected channel");
            System.out.println("Total Revenue:" + "$" + mol.getSalesVolume());
            System.out.println("Target Sales: $"+faker.number().numberBetween(1000, 50000));
            System.out.println("Total Number of Orders: " + faker.number().numberBetween(1, 100));
            System.out.println("Top Supplier: " + topSupplier.getName());
            System.out.println("Top Product:" + faker.commerce().productName() + " " +
                    "From Supplier: " + sd.getSupplierList().get(0).getName());
            System.out.println("Most Successful Market:" + generateRandomMarket() + " $" +
                    faker.number().numberBetween(1, 1000));
            System.out.println(
                    "Most Successful Advertisements via this Channel: " + faker.commerce().department() + " Ads");
            System.out.println("Best Selling Bundle: " + "Bundle" + " " + random.nextInt(50) + 1);
            System.out.println("Advertisement Budget: $" +faker.number().numberBetween(1500, 20000));
        }
    }
}