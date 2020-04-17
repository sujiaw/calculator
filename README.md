# Prerequisites
* Java 1.8
* Maven 3.5+ (to build yourself)

# Use pre-built prn calculator
1. cd build
2. tar -xvf calc.tar
3. cd calc/bin
4. bash start.sh
5. enter formula and see computed result immediately
6. enter "exit" in terminal or run (bash stop.sh) to quit

# Build
1. make clean
2. make
3. calc.tar will be created in folder ./release

# Run
1. tar -xvf calc.tar
2. cd calc/bin
3. bash start.sh

# Stop
1. enter "exit" in terminal or run (bash stop.sh)

# Unit Test
1. make test