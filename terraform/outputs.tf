output "project_id" {
  description = "Id del proyecto Atlas"
  value       = mongodbatlas_project.this.id
}

output "cluster_name" {
  description = "Nombre del cluster"
  value       = mongodbatlas_advanced_cluster.this.name
}

output "db_username" {
  description = "Usuario de base de datos configurado"
  value       = mongodbatlas_database_user.app_user.username
}

output "connection_string_hint" {
  description = "Hint para construir MONGODB_URI (obtén el SRV desde Atlas UI)"
  value       = "mongodb+srv://${var.db_username}:<password>@<cluster-url>/${var.db_name}?retryWrites=true&w=majority"
}
