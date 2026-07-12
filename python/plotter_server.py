import socket
from collections import deque
import matplotlib.pyplot as plt

HOST = "127.0.0.1"
PORT = 5000

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind((HOST, PORT))
server.listen(1)

print("Waiting for Java...")

client, address = server.accept()
print(f"Connected to {address}")

client_file = client.makefile("r")

plt.ion()

fig, ax = plt.subplots()

# Speichert maximal 1500 Werte
values = deque(maxlen=500)

# Plot nur EINMAL erzeugen
line_plot, = ax.plot([], [])

ax.set_title("Values from Java")
ax.set_xlabel("Sample")
ax.set_ylabel("Value")
ax.set_ylim(0, 0.01)

print("Plotting...")

while plt.fignum_exists(fig.number):

    line = client_file.readline()

    # Java disconnected
    if not line:
        break

    value = float(line.strip())
    values.append(value)

    # Daten der vorhandenen Linie aktualisieren
    line_plot.set_data(range(len(values)), values)

    # X-Achse an aktuelle Datenmenge anpassen
    ax.set_xlim(0, max(len(values), 1))

    # Nur neu zeichnen
    fig.canvas.draw_idle()
    fig.canvas.flush_events()

print("Exiting...")

client.close()
server.close()