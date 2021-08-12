package com.vanniktech.rxriddles

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers


object Riddle102 {
    /**
     * Execute both [first] and [second] Single's in parallel and provide both results as a pair.
     *
     * Use case: Execute two network requests in parallel and wait for each other and process the combined data.
     */
    fun solve(first: Single<Int>, second: Single<Int>): Single<Pair<Int, Int>> {

        val x: (Int, Int) -> Pair<Int, Int> = { f, s -> f to s }
        val y: (Int, Int) -> Int = fun(a: Int, b: Int): Int = 2
        val z: BiFunction<Int, Int, Int> = BiFunction { f, s -> f + s }
        val solution: BiFunction<Int, Int, Pair<Int, Int>> = BiFunction { f, s -> f to s }

        return Single.zip(first.subscribeOn(Schedulers.computation()), second.subscribeOn(Schedulers.computation()), solution)
    }
}
