set terminal pdfcairo enhanced color size 29cm,20cm font "Times-New-Roman"  fontscale 1.2
set xlabel "{/:Italic x}"
set ylabel "{/:Italic p}"
set yrange [0:0.016]
set xrange[-150:150]
set output "randomWalk.pdf"
set title "Random Walk ({/:Italic t} = 1000)"
set style fill solid border lc rgb "black"
p = 0.5
t = 1000
s = sqrt(4*t*p*(1-p))
mu = t*(2*p-1)
f(x) = (1/sqrt(2*pi*s*s))*exp(-(x-mu)*(x-mu)/2/s/s)
plot "Simulation-output-100000.txt" with boxes notitle,f(x) lt 8 lw 5 notitle
