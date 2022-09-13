package com.swissborg.cryptomarket.network

interface BaseMapper<Remote, Local> {
    fun map(remote: Remote): Local
}