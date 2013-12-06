set autoscale

set key font ",15"
set title font ",35"
set xlabel font ",25"
set ylabel font ",25"
set key top right

set title "MCTS Modifed Random vs Rule Based"
set xlabel "MCTS PlayOuts"
set ylabel "Score after 200 games"
set xrange [0:1000]
plot "modifiedGenerated.txt" using 1:2 title 'MCTS', \
	 "modifiedGenerated.txt" using 1:3 title 'Rule Based',\
	 "modifiedCalculated.txt" using 1:4 title 'MCTS Median' w linespoints,\
	 "modifiedCalculated.txt" using 1:5 title 'Rule Based Median' w linespoints;
