package s.mesonero.cryptoinformer.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class CryCommonUseCase<T> {
   suspend fun runTask (task: () -> T): T {
       return withContext(Dispatchers.IO) {
           // Blocking network request code
           return@withContext task.invoke()
       }
   }
}