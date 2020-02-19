    package com.example.braintrainer;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.gridlayout.widget.GridLayout;

    import android.os.Bundle;
    import android.os.CountDownTimer;
    import android.view.View;
    import android.widget.Button;
    import android.widget.LinearLayout;
    import android.widget.TextView;

    import java.util.ArrayList;
    import java.util.Random;

    public class MainActivity extends AppCompatActivity {
        TextView myFirstTextView;
        TextView mySixthTextView;
        TextView myThirdTextView;
        TextView myFourthTextView;
        TextView myFifthTextView;
        TextView mySecondTextView;
        Button button;
        Button button2;
        Button button3;
        Button button4;
        Button playAgain;
        CountDownTimer countDownTimer;
        GridLayout myGridLayout;
        LinearLayout myLinearLayout;
        boolean countDownIsActive = true;


        ArrayList<Integer> answer  = new ArrayList<>();
        int locationOfCorrectAnswer;
        int score = 0;
        int nextQuestion = 0;


        public void playAgain(View view)
        {

            myFourthTextView.setText("");

            score=0;
            nextQuestion =0;
            playAgain.setVisibility(View.INVISIBLE);
            myFifthTextView.setText("");
            myThirdTextView.setText("0/0");
            myFirstTextView.setText("00:30");
            quizUpdate();

            countDownTimer = new CountDownTimer(30000+ 100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {

                    myFourthTextView.setText("Your score is :" + String.format("%s/%s", Integer.toString(score), Integer.toString(nextQuestion)));

                    playAgain.setVisibility(View.VISIBLE);
                    countDownIsActive=false;
                    myFirstTextView.setText("00:00");

                   /* myFourthTextView.setVisibility(View.INVISIBLE);*/


                }
            }.start();

        }



        public void quizUpdate(){
            Random rand = new Random();

            int firstNum = rand.nextInt(21);
            int secondNum = rand.nextInt(21);
            mySecondTextView.setText(String.format("%s+%s", Integer.toString(firstNum), Integer.toString(secondNum)));


            locationOfCorrectAnswer = rand.nextInt(4);
            int incorrectAnswer;
            answer.clear();
            for(int i=0;i<4;i++) {

                if(i==locationOfCorrectAnswer){

                    answer.add(firstNum+secondNum);
                }else{
                    incorrectAnswer = rand.nextInt(41);

                    while(incorrectAnswer==firstNum+secondNum){
                        incorrectAnswer = rand.nextInt(41);
                    }
                    answer.add(incorrectAnswer);
                }

            }

            button.setText(String.format("%s",Integer.toString(answer.get(0))));
            button2.setText(String.format("%s",Integer.toString(answer.get(1))));
            button3.setText(String.format("%s",Integer.toString(answer.get(2))));
            button4.setText(String.format("%s",Integer.toString(answer.get(3))));
}


        public void answerChecker(View view){


            if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

                score++;

                myFourthTextView.setText(R.string.txt_view4);

            }
            else{

                myFourthTextView.setText(R.string.txt_view5);

            }

            nextQuestion++;
            myThirdTextView.setText(String.format("%s/%s", Integer.toString(score), Integer.toString(nextQuestion)));
            quizUpdate();

        }


        public void Start(final View view){

            myGridLayout.setVisibility(View.VISIBLE);
            myLinearLayout.setVisibility(View.VISIBLE);
            mySixthTextView.setVisibility(View.INVISIBLE);
            playAgain.setVisibility(View.INVISIBLE);
}

        public void updateTimer(int secondsLeft){

            int minutes = secondsLeft/60;
            int seconds = secondsLeft-(minutes*60);

            String minutesMinutes = Integer.toString(minutes);
            if(minutes<=9)
            minutesMinutes = "0" + minutesMinutes;

            String secondSecond = Integer.toString(seconds);
            if(seconds<=9)
                secondSecond = "0" + secondSecond;

            myFirstTextView.setText(String.format("%s:%s",minutesMinutes,secondSecond));

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mySecondTextView = findViewById(R.id.textView2);


            button = findViewById(R.id.button);
            button2 = findViewById(R.id.button2);
            button3 = findViewById(R.id.button3);
            button4 = findViewById(R.id.button4);
            myGridLayout = findViewById(R.id.gridLayout);
            mySixthTextView = findViewById(R.id.textView6);
            myLinearLayout = findViewById(R.id.linearLayout);
            myFourthTextView = findViewById(R.id.textView4);
            myFirstTextView = findViewById(R.id.textView);
            myThirdTextView = findViewById(R.id.textView3);
            myFifthTextView = findViewById(R.id.textView5);
            playAgain = findViewById(R.id.button5);

            playAgain.setVisibility(View.INVISIBLE);

            playAgain(findViewById(R.id.button5));

        }

        }

