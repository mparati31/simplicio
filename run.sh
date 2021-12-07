#! /bin/bash

cd simplicio

if [ "$#" -ne 1 ]; then
    echo "Atteso un parametro"
else
    if [ ! -d "output" ]
    then
        mkdir output
    fi

    cd out/
    java -cp $ANTLR4_JAR: Main $1 > ../output/latex.tex
    cd ../output/
    latex latex.tex
    xdg-open latex.dvi
fi
