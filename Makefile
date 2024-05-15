run-deps: ## Run dependencies
	cd product-manager-docker && docker-compose -f compose.yaml up

migration: ## Execute DB migrations (Not implemented yet)
	./gradlew -Dflyway.configFiles=flyway.conf flywayMigrate

clean-db: ## reset db (Not implemented yet)
	./gradlew -Dflyway.configFiles=flyway.conf flywayClean