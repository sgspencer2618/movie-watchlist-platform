package interface_adapters.movie_info;

public class MovieInfoState {

    private String titleText;
    private String infoText;
    private String summaryText;
    private String talentText;
    private String imdbScore;
    private String rottenTomatoesScore;
    private String metacriticScore;
    private String posterURL;

    public MovieInfoState(MovieInfoState copy) {
        this.titleText = copy.titleText;
        this.infoText = copy.infoText;
        this.summaryText = copy.summaryText;
        this.talentText = copy.talentText;
        this.imdbScore = copy.imdbScore;
        this.rottenTomatoesScore = copy.rottenTomatoesScore;
        this.metacriticScore = copy.metacriticScore;
        this.posterURL = copy.posterURL;
    }

    // empty constructor
    public MovieInfoState() { }

    public String getTitleText() { return titleText; }

    public void setTitleText(String titleText) { this.titleText = titleText; }

    public String getInfoText() { return infoText; }

    public void setInfoText(String infoText) { this.infoText = infoText; }

    public String getSummaryText() { return summaryText; }

    public void setSummaryText(String summaryText) { this.summaryText = summaryText; }

    public String getTalentText() { return talentText; }

    public void setTalentText(String talentText) { this.talentText = talentText; }

    public String getImdbScore() { return imdbScore; }

    public void setImdbScore(String imdbScore) { this.imdbScore = imdbScore; }

    public String getRottenTomatoesScore() { return rottenTomatoesScore; }

    public void setRottenTomatoesScore(String rottenTomatoesScore) { this.rottenTomatoesScore = rottenTomatoesScore; }

    public String getMetacriticScore() { return metacriticScore; }

    public void setMetacriticScore(String metaCriticScore) { this.metacriticScore = metaCriticScore; }

    public String getPosterURL() { return posterURL; }

    public void setPosterURL(String posterURL) { this.posterURL = posterURL; }
}
