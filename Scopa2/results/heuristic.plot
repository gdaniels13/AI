set autoscale
set key font ",15"
set title font ",35"
set xlabel font ",25"
set ylabel font ",25"
set key top right

set title "Rule Based vs MinMax Rule Based"
set xlabel "Game #"
set ylabel "Score after 200 games"
set yrange [395:540]
set xtic 2

set style fill solid border -1
num_of_categories=2
set boxwidth .5 #0.5/num_of_categories
offset=-0.5
plot "heuristicGenerated" using ($1+offset):2 title 'MinMax' smooth freq with boxes, \
	 "heuristicGenerated" using 1:3 title 'Regular' smooth freq with boxes,\
	 "heuristicCalculated.txt" using ($1+offset):4 title 'MinMax Median' w boxes,\
	 "heuristicCalculated.txt" using 1:5 title 'Regular Median' w boxes;
