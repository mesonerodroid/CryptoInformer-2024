package s.mesonero.cryptoinformer

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class CryCommonUseCase<T> {
   suspend fun runTask (task: () -> Unit) {
       return withContext(Dispatchers.IO) {
           // Blocking network request code
           return@withContext task.invoke()
       }
   }
}