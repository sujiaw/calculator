.PHONY: all build-local test clean

all: build-local

build-local:
	rm -rf ./release
	mvn -U -DskipTests clean package

test:
	mvn -U clean '-Dtest=com.airwallex.calc.unit.*.*Test' -DfailIfNoTests=false test

clean:
	rm -rf ./release
	mvn clean