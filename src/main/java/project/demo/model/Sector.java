package project.demo.model;

public enum Sector {
    AGRICULTURE_HORTICULTURE("Land- & Tuinbouw"),
    BUSINESS_SERVICES("Zakelijke dienstverlening"),
    CONSTRUCTION("Bouw"),
    CULTURE_SPORTS_RECREATION("Cultuur, Sport & Recreatie"),
    ENERGY_WATER_ENVIRONMENT("Energie, Water & Milieu"),
    FINANCE("Financiële Instellingen"),
    HEALTHCARE("Gezondheid"),
    HOSPITALITY("Horeca"),
    ICT_AND_MEDIA("ICT & Media"),
    INDUSTRIAL("Industrie"),
    LOGISTICS("Logistiek"),
    OTHER("Overige"),
    RETAIL("Detailhandel"),
    WHOLESALE("Groothandel");

    private final String label;

    Sector(String label) {
        this.label = label;
    }

    /**
     * Method for getting the Enum value instead of the (front end used) String
     * @param label Front end used String of the Enum
     * @return      The actual value of the Enum OR Null if the label variable is incorrect
     */
    public static Sector valueOfLabel(String label){
        for (Sector s : values()) {
            if (s.label.equals(label)){
                return s;
            }
        }
        return null;
    }
}
