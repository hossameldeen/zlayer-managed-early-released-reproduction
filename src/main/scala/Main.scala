import zio._
import zio.interop.catz._
import skunk.{Session, Strategy}
import skunk.codec.all._
import skunk._
import skunk.implicits._

object Main extends App {

  type ZSessionPool = Managed[Throwable, Session[Task]]

  def mkSessionPool(): Managed[Throwable, ZSessionPool] = {
    import natchez.Trace.Implicits.noop
    import zio.interop.catz._

    Session
      .pooled[Task](
        host = "localhost",
        port = 5432,
        user = "postgres",
        database = "zlayer-managed-early-released-reproduction-db",
        password = Some("postgres"),
        max = 10,
        strategy = Strategy.SearchPath
      )
      .toManagedZIO
      .map { resource =>
        resource.toManagedZIO
          .tapM(_ => UIO.succeed(println("Database session acquired")))
          .ensuring(UIO.succeed(println("Database session released")))
      }
  }

  val live: ZLayer[Any, Throwable, Has[ZSessionPool]] = {
    ZLayer.fromManaged(
      mkSessionPool()
    )
  }

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
    (for {
      // Will be used in "Arrange" and "Assert" phases
      zSessionPool <-
        ZIO
          .access[Has[ZSessionPool]](_.get)
          .provideLayer(live)

      // Arrange
      _ <- zSessionPool.use { session =>
        session.prepare(sql"""SELECT 'abc'""".query(varchar)).use { pq =>
          pq.unique(Void)
        }
      }
    } yield ()).exitCode
  }
}
