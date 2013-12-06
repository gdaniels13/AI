set autoscale
set key font ",15"
set title font ",35"
set xlabel font ",25"
set ylabel font ",25"
set key top right


set title "MCTS Random vs Rule Based"
set xlabel "MCTS PlayOuts"
set ylabel "Score after 200 games"
set xrange [0:1050]
set xtics 100
plot "preRandomeGenerated.txt" using 1:2 title 'MCTS', \
	 "preRandomeGenerated.txt" using 1:3 title 'Rule Based',\
	 "preRandomCalculated.txt" using 1:4 title 'MCTS Median' w linespoints,\
	 "preRandomCalculated.txt" using 1:5 title 'Rule Based Median' w linespoints;
