FROM clojure:openjdk-11-tools-deps

ADD . /app

WORKDIR /app

ENTRYPOINT ["clj", "-m", "main"]