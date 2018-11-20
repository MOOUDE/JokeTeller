package com.example.jokerlib;


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Joke {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

    @Persistent
    private String jokeString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJokeString() {
        return jokeString;
    }

    public void setJokeString(String jokeString) {
        this.jokeString = jokeString;
    }
    /*  private final String someJokes[] = {
            "Q. Why do Dasher and Dancer love coffee?\n" +
                    "A. Because they're Santa's star bucks!\n",
            "Did you hear the one about the little mountain?\n" +
                    "It's hill-arious!",
            "Q. What's a banana peel's favorite type of shoe?\n" +
                    "A. Slippers!\n ",
            "Q. What does the Gingerbread Man use to make his bed?\n" +
                    "A. Cookie sheets!",
            "Q. What do you call an outlaw who steals gift wrapping from the rich to give to the poor?\n" +
                    "A. Ribbon Hood.",
            "Q. What do you get if you cross a Christmas tree with an iPad?\n" +
                    "A. A pineapple!\n" +
                    "By Zoey Y., Flower Mound, Tex."
    };

    public String getRandomJoke(){
        Random rand = new Random();
        int value = rand.nextInt(someJokes.length - 1);
        return someJokes[value];
    } */




}
