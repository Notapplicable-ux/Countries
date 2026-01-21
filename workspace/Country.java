public class Country 
{
    // private instance variables
    private String name;
    private String capital;
    private String primaryLanguage;
    private String imageFile;

    // default constructor
    public Country() 
    {
    }

    // constructor with 4 arguments
    public Country(String name, String capital, String primaryLanguage, String imageFile) 
    {
        this.name = name;
        this.capital = capital;
        this.primaryLanguage = primaryLanguage;
        this.imageFile = imageFile;
    }

    // accessor (getter) methods
    public String getName() 
    {
        return name;
    }

    public String getCapital() 
    {
        return capital;
    }

    public String getPrimaryLanguage() 
    {
        return primaryLanguage;
    }

    public String getImageFile() 
    {
        return imageFile;
    }

    // toString method
    public String toString() 
    {
        return name + "'s capital is " + capital +
               " and its primary language is " + primaryLanguage + ".";
    }
}
