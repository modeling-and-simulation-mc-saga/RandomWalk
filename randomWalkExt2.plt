set terminal pdfcairo enhanced color size 29cm,20cm font "Times-New-Roman"  fontscale 1.2
set xlabel "{/:Italic x}"
set ylabel "{/:Italic p}"
#set yrange [0:0.016]
#set xrange[-50:50]
set output "ExpSample.pdf"
set title "Random Walk ({/:Italic t} = 1000)"
set style fill solid border lc rgb "black"
t = 1000
e = exp(1)
mu = t * (e-2)/(e-1)
ss = (e*e-3*e+1)/(e-1)/(e-1)
s = sqrt(t*ss)
f(x) = (1/sqrt(2*pi*s*s))*exp(-(x-mu)*(x-mu)/2/s/s)
plot "ExpSample-output-100000.txt" with boxes notitle,f(x) lt 8 lw 5 notitle
