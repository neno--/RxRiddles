package com.vanniktech.rxriddles

import io.reactivex.rxjava3.core.Observable

object Riddle19 {
    /**
     * Use the given [Interaction] interface and use its listener to transform the [Int] callback to an Observable that emits every time the listener is called.
     * When the Observable is being disposed the listener should be set to null.
     *
     * Use case: Transform any listener into an Observable.
     */
    fun solve(interaction: Interaction): Observable<Int> {
        val observable: Observable<Int>
        observable = Observable.create<Int> { emitter ->
            val callback = fun(int: Int): Unit {
                emitter.onNext(int)
            }
            interaction.listener = callback
            emitter.setCancellable { interaction.listener = null }
        }
        return observable
    }

    fun solveLool(interaction: Interaction): Observable<Int> {
        var c = 0
        val observable: Observable<Int>
        observable = Observable.create<Int> { emitter ->
            val callback = fun(int: Int): Unit {
                c++
                emitter.onNext(int)
                if (c == 2) interaction.listener = null
            }
            interaction.listener = callback
        }
        return observable
    }

    interface Interaction {
        var listener: ((Int) -> Unit)?
    }
}
