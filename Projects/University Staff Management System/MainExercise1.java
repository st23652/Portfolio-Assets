class Exercise1 {
    //checking negative numbers and also checks that 2nd num is bigger then the first one
    public void printOddsBetween(int no1, int no2){
        if (no1<0 || no2<0 || no2 < no1){
            return;
        }
        //checks if the num is odd and prints while incrementing it by 1
        for (int i = no1; i <= no2; i++){
            if (i % 2 != 0){
                System.out.println(i);
            }
        }
    }
    public String getABCUpTo(char s1){
        //creates a string builder object
        StringBuilder s2 = new StringBuilder();
        //checks if it is a letter
        //Character.isLetter(s1);
        if (Character.isLetter(s1)) {
            //making all inputs lower case for same result
            char lowercase = Character.toLowerCase(s1);
        //97 is the conversion to ascii code of lowercase a
            for (int i = 97; i <= lowercase; i++){
            //conversion from int to char and add it to the string builder
                s2.append((char)i);
        }
        }
        else {
            //data type is mentioned
            return"";
        }
        //converts string builder object to string
        return s2.toString();
    }
}

