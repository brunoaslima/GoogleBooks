package br.com.brunolima.googlebooks.model

data class Volume(
    val id: String,
    val selfLink: String,
    val volumeInfo: VolumeInfo
)