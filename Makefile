	.PHONY: all build env-up env-down clean

	IMAGE_NAME = dojo-spring-mongo
	COMPOSE_FILE = docker-compose.yml
	COMPOSE_FILE_BUILD = docker-compose-app.yml

	build:
		docker-compose -f $(COMPOSE_FILE_BUILD) up -d

	env-up:
		docker-compose -f $(COMPOSE_FILE) up -d

	env-down:
		docker-compose -f $(COMPOSE_FILE) down

	clean:
		docker image prune -f
