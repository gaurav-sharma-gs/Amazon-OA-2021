A group of work friends at Amazon is playing a competitive video game together. During the game, each player receives a certain amount of points based on their performance. At the end of a round, players who achieve at least a cutoff rank get to "level up" their character, gaining increased abilities for them. Given the scores of the players at the end of the round, how many players will be able to level up their character?
//
//        Note that players with equal scores will have equal ranks, but the player with the next lowest score will be ranked based on the position within the list of all players' scores. For example, if there are four players, and three players tie for first place, their ranks would be 1,1,1, and 4. Also, no player with a score of O can level up, no matter what their rank.
//
//        Write an algorithm that returns the count of players able to level up their character.
//
//        Input
//
//        The input to the function/method consists of three arguments:
//
//        cutOffRank, an integer representing the cutoff rank for levelin up the player's character;
//
//        num, an integer representing the total number of scores;
//
//        scores, a list of integers representing the scores of the players.
//
//        Output
//
//        Return an integer representing the number of players who will be able to level up their characters at the end of the round.
//
//        Constraints
//
//        1 <= num <= 10^5
//
//        0 <= scores[i] <= 100
//
//        0 <= i < num
//
//cutOffRank <= num
//
//        Examples
//
//        Example 1:
//
//        Input:
//
//        cutOffRank = 3
//
//        num= 4
//
//        scores=[100, 50, 50, 25 ]
//
//        Output:
//
//        3
//
//        Explanation:
//
//        There are num= 4 players, where the cutOffRank is 3 and scores = [100, 50,50, 25]. These players' ranks are [1, 2, 2, 4]. Because the players need to have a rank of at least 3 to level up their characters, only the first three players will be able to do so.
//
//        So, the output is 3.
//
//        Example 2:
//
//        Input:
//
//        cutOffRank = 4
//
//        num=5
//
//        scores=[2,2,3,4,5]
//
//        Output:
//
//        5
//
//        Explanation:
//
//        In order, the players achieve the ranks [4,4,3,2,1]. Since the cutOffRank is 4, all 5 players will be able to level up their characters.
//
//        So, the output is 5.



/////////////////////////////////////////////////////////////////Found on Internet/////////////////////////////////////////////////////////////////
public int cutOffRank(int cutOffRank, int num, int[] scores) {
        if(cutOffRank == 0) return 0;
        if(cutOffRank == num) return num;

        Arrays.sort(scores);
     
        if(scores[num-1] == 0) return 0;
        
        int count = 0;
        int i = num - cutOffRank;
        while(scores[i] == 0){
            count--;
            i++;
        }
        
        
        while(i > 0){
            if(scores[i] != scores[i-1]){
                break;
            }
            i--;
            count++;
        }
        
        return cutOffRank + count;
        
    }
    
/////////////////////////////////////////////////////////////////Found on Internet/////////////////////////////////////////////////////////////////
    
  /* package codechef; // don't place package name! */

import java.util.;
import java.lang.;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{

 public static int cutOffRank(int cutOffRank, int num, int[] scores) {
    
    TreeMap<Integer,Integer> ts = new TreeMap<>(Collections.reverseOrder());
    
    for(int x: scores){
        ts.put(x,ts.getOrDefault(x,0)+1);
    }
    
    int res = 0;
    
    for(Map.Entry<Integer,Integer> e : ts.entrySet()){
        
        if(cutOffRank>0 && e.getKey()>0){
            res+= e.getValue();
        }
        cutOffRank-=e.getValue() ;
        
    }
    
    
    return res;
}

public static void main (String[] args) throws java.lang.Exception
{
    int arr[] = {2,2,3,4,5};
	// your code goes here
	System.out.println(cutOffRank(4,5,arr));
}
}


/////////////////////////////////////////////////////////////////Written By Gaurav/////////////////////////////////////////////////////////////////
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.*;

class Solution {
    public static int cutOffRank(int cutOff, List<Integer> scores) {
        //int[] score = (int[])scores.toArray();
        int num = scores.size(); 
        quickSort(scores, 0, num-1); // figure out descending sort for quickSort
        List<Integer> descScores = new ArrayList<>();
        for(int i = scores.size()-1; i >= 0;i--){
        descScores.add(scores.get(i));
        }
        int ranks[]= assignRanks(descScores, num);
        for(int i =0; i < ranks.length;i++){
        //System.out.println(ranks[i]);
        }
        return findNoOfLevelUpPlayers(ranks, cutOff);
    }
    
    private static int findNoOfLevelUpPlayers(int[] rank, int cutOff){
        int i = 0;
        for(int j = 0; j < rank.length; j++){
            if(rank[j] > cutOff){
                break;
            }
            i++;
        }
        return i;
    }
    
    private static int[] assignRanks(List<Integer> scores,int num){
        int[] ranks = new int[num];
        int len = 0;
        for(int i = 0; i < num; i++){
            //System.out.println(i);
            len = i;
            ranks[i] = i+1;
            while(len<num-1 && scores.get(len) == scores.get(len+1)){
                ranks[++len] = i+1;
            }
            i = len;
            //System.out.println(i);
        }
        return ranks;
    }
    
    private static void quickSort(List<Integer> arr,int low, int high ){
        if(low<high){
        int part = partition(arr, low,high);
        quickSort(arr, low, part-1);
        quickSort(arr, part+1, high);
        }
    }
    
    private static int partition(List<Integer> arr, int low, int high){
        int pivot = arr.get(high);
        int i = (low-1);
        for(int j = low; j<high;j++){
            if(arr.get(j) <=pivot){
                i++;
                int temp = arr.get(i);
                arr.set(i,arr.get(j));
                arr.set(j, temp);
            }
        }
        int temp = arr.get(i+1);
        arr.set(i+1, arr.get(high));
        arr.set(high, temp);
        
        return i+1;
    }
    
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cutOff = Integer.parseInt(scanner.nextLine());
        List<Integer> scores = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        int res = cutOffRank(cutOff, scores);
        System.out.println(res);
    }
}

