FROM python:3 AS python-app

WORKDIR /usr/src/app

COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt

COPY . .

EXPOSE 8081

CMD ["fastapi", "run", "FastApiServer.py", "--port", "8081"]
