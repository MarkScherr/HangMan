/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hangman.controller;

import com.sg.hangman.models.GuessResponse;
import com.sg.hangman.models.Word;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mrsch
 */
@CrossOrigin
@RestController
@RequestMapping("/hangman")
public class Game {
    private static final ArrayList<String> wordArray = new ArrayList<>(Arrays.asList("abruptly","absurd","abyss","affix","askew","avenue","awkward","axiom","azure","bagpipes",
            "bandwagon","banjo","bayou","beekeeper","bikini","blitz","blizzard","boggle","bookworm","boxcar","boxful","buckaroo",
            "buffalo","buffoon","buxom","buzzard","buzzing","buzzwords","caliph","cobweb","cockiness","croquet","crypt","curacao",
            "cycle","daiquiri","dirndl","disavow","dizzying","duplex","dwarves","embezzle","equip","espionage","euouae","exodus","faking",
            "fishhook","fixable","fjord","flapjack","flopping","fluffiness","flyby","foxglove","frazzled","frizzled","fuchsia","funny","gabby",
            "galaxy","galvanize","gazebo","giaour","gizmo","glowworm","glyph","gnarly","gnostic","gossip","grogginess","haiku",
            "haphazard","hyphen","iatrogenic","icebox","injury","ivory","ivy","jackpot","jaundice","jawbreaker","jaywalk","jazziest",
            "jazzy","jelly","jigsaw","jinx","jiujitsu","jockey","jogging","joking","jovial","joyful","juicy","jukebox","jumbo","kayak","kazoo",
            "keyhole","khaki","kilobyte","kiosk","kitsch","kiwifruit","klutz","knapsack","larynx","lengths","lucky","luxury","lymph",
            "marquis","matrix","megahertz","microwave","mnemonic","mystify","naphtha","nightclub","nowadays","numbskull",
            "nymph","onyx","ovary","oxidize","oxygen","pajama","peekaboo","phlegm","pixel","pizazz","pneumonia","polka",
            "pshaw","psyche","puppy","puzzling","quartz","queue","quips","quixotic","quiz","quizzes","quorum","razzmatazz",
            "rhubarb","rhythm","rickshaw","schnapps","scratch","shiv","snazzy","sphinx","spritz","squawk","staff","strength",
            "strengths","stretch ","stronghold","stymied","subway","swivel","syndrome","thriftless","thumbscrew","topaz",
            "transcript","transgress","transplant","triphthong","twelfth","twelfths","unknown","unworthy","unzip","uptown",
            "vaporize","vixen","vodka","voodoo","vortex","voyeurism","walkway","waltz","wave","wavy","waxy","wellspring",
            "wheezy","whiskey","whizzing","whomever","wimpy","witchcraft","wizard","woozy","wristwatch","wyvern","xylophone",
            "yachtsman","yippee","yoked","youthful","yummy","zephyr","zigzag","zigzagging","zilch","zipper","zodiac","zombie"
    ));
        
    @GetMapping 
    public Word getWord () {
        Word word = new Word();
        
       Random  rdm = new Random();
       word.setId(rdm.nextInt(212)+0);
       word.setWord(wordArray.get(word.getId()));

       return word;
 
    }
    
    @PutMapping("/{guess}/{id}")
    public ArrayList<GuessResponse> checkGuess(@PathVariable String guess , @PathVariable int id) { 
        ArrayList<GuessResponse> returnList = new ArrayList<>();
        String word = wordArray.get(id);
        GuessResponse gr = new GuessResponse();
       char guessChar = guess.charAt(0);
        for (int i = 0 ; i < word.length() ; i++) {
            if (guessChar == (word.charAt(i))) {
                gr.setStatus("correctGuess");
                gr.setLocationInWord(i);
                returnList.add(gr);
                gr = new GuessResponse();
                //return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            } 
   
        }
        if (returnList.isEmpty()) {
            gr.setStatus("incorrectGuess");
            gr.setLocationInWord(-1);
            returnList.add(gr);
        }
        
 //       return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).build();
       return returnList;

}
}