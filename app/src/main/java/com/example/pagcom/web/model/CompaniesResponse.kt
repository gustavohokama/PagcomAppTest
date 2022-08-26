package com.example.pagcom.web.model

data class CompaniesResponse(
    val id: String?,
    val cd_acao: String?,
    val cd_acao_rdz: String?,
    val nm_empresa: String,
    val pctl_ctra: String,
    val qtd_teorica: String,
    val created_at: String
) {
}