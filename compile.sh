cd simplicio/grammar/
echo "Generating lexer and parser..."
java -jar $ANTLR4_JAR -visitor -no-listener -o ../src/antlr/ MathExpression.g4
cd ../src/
echo "Compiling..."
javac -cp $ANTLR4_JAR: -d ../out/ Main.java
echo "Done."
